Clone RML-Mapper
----------------

	git clone --recursive git@git.mmlab.be:rml/RML-Mapper.git

Build RML-Mapper
----------------

	mvn clean install

Run RML-Mapper
-------------

	java -jar RML-Processor/target/RML-Processor-0.2.jar -m <mapping_doc> -o <output_file> [-f <file_format> -tm <triples_map> -p <parameter> -g <graph>]
