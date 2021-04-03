cd target
mkdir evictionsparser
cp *SNAPSHOT.jar evictionsparser/parser.jar
cp ../parse ../parse.bat evictionsparser/

zip evictionsparser.zip evictionsparser/*
