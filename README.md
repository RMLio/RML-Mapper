RML Mapper
==========

Clone RML-Mapper
----------------

	git clone --recursive git@git.mmlab.be:rml/RML-Mapper.git

	git checkout metadata

	git submodule update --init --recursive


Build RML-Mapper
----------------

Run 

	bin/RML-Mapper


Run RML-Mapper
-------------

	bin/RML-Mapper -m <mapping_doc> -o <output_file> [-b <baseIRI> -f <file_format> -tm <triples_map> -p <parameter> -g <graph> -md <metadata_vocab> - mdl <metadata_level> -mdf <metadata_format> -p <arguments> ]
