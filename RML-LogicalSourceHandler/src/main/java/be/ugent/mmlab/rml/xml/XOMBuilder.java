package be.ugent.mmlab.rml.xml;

import jlibs.core.lang.ImpossibleException;
import jlibs.xml.sax.dog.NodeItem;
import jlibs.xml.sax.dog.NodeType;
import jlibs.xml.sax.dog.sniff.Event;
import jlibs.xml.sax.dog.sniff.XMLBuilder;
import nu.xom.Attribute;
import nu.xom.Comment;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Node;
import nu.xom.ParentNode;
import nu.xom.ProcessingInstruction;
import nu.xom.Text;

/**
 * RML Processor
 *
 * @author mielvandersande
 */
public class XOMBuilder extends XMLBuilder {

    private ParentNode curNode;

    @Override
    protected Object onStartDocument() {
        return curNode = null;
    }

    @Override
    protected Object onStartElement(Event event) {
        Element element = new Element(event.qualifiedName(), event.namespaceURI());

        /*if (document == null) {
            document = new Document(element);
        }*/

        if (curNode != null) {
            curNode.appendChild(element);
        }
        return curNode = element;
    }

    @Override
    protected Object onEvent(Event event) {
        
        
        switch (event.type()) {
            case NodeType.ATTRIBUTE:
                Attribute attr = new Attribute(event.qualifiedName(), event.namespaceURI(), event.value());
                if (curNode != null) {
                    ((Element) curNode).addAttribute(attr);
                }
                return attr;
            case NodeType.COMMENT:
                Comment comment = new Comment(event.value());
                if (curNode != null) {
                    curNode.appendChild(comment);
                }
                return comment;
            case NodeItem.PI:
                ProcessingInstruction pi = new ProcessingInstruction(event.localName(), event.value());
                if (curNode != null) {
                    curNode.appendChild(pi);
                }
                return pi;
            case NodeItem.TEXT:
                Text text = new Text(event.value());

                if (curNode != null && !(curNode instanceof Document)) {
                    curNode.appendChild(text);
                }
                return text;
            case NodeItem.NAMESPACE:

                if (curNode != null) {
                    ((Element) curNode).addNamespaceDeclaration(event.localName(), event.value());
                }
                return curNode;
        }
        
        
        
        throw new ImpossibleException("event.type: " + event.type());
    }

    @Override
    protected Object onEndElement() {
        return curNode = curNode.getParent();
    }

    @Override
    protected void onEndDocument() {

    }

    @Override
    protected void clearCurNode() {
        curNode = null;
    }

    @Override
    protected void removeFromParent(Object node) {
        ((Node) curNode).detach();
    }

    @Override
    protected boolean hasParent() {
        return curNode.getParent() != null;
    }
}
