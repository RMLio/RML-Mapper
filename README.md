RML Mapper
==========

> ALERT! Version 4 is out! RMLMapper is _rebuilt from the ground up_ on another repository ([rmlmapper-java](https://github.com/RMLio/rmlmapper-java)).
All future development will now happen there. This repository remains available as legacy.

Clone RML-Mapper
----------------

	git clone --recursive https://github.com/RMLio/RML-Mapper.git
	git submodule update --init --recursive

Build RML-Mapper
----------------

Run 

	bin/RML-Mapper
  
Testing RML-Mapper
------------------

Copy ./resources into RML-Processor

`mvn test`


Run RML-Mapper
-------------

	bin/RML-Mapper -m <mapping_doc> -o <output_file> [-b <baseIRI> -f <file_format> -tm <triples_map> -p <parameter> -g <graph> -md <metadata_vocab> - mdl <metadata_level> -mdf <metadata_format> -p <arguments> ]	


Update RML-Mapper
-----------------

    git pull --recurse-submodules

Extension FnO
-------------

Data processing instructions can be added in mapping documents, and their implementations can be made available
by including additional `.jar` and `.java` files under `resources/functions` of the **current working directory**.
Also, a `metadata.json`-file needs to be present that includes additional metadata to allow for automatic processing.
You can inspect (and/or copy) the `resources/functions`-folder of this repo to your own working directory.
See https://github.com/fnoio for more implementations.
