package be.ugent.mmlab.rml.logicalsourcehandler.termmap;

import be.ugent.mmlab.rml.vocabularies.QLVocabulary.QLTerm;

/**
 * RML Processor
 *
 * @author andimou
 */
public interface TermMapProcessorFactory {
    /**
     *
     * @param term
     * @return
     */
    public  TermMapProcessor create(QLTerm term);
}
