default:
	javac src/*.java -d out/
run:
	java -cp out/ TextEditor
clean:
	rm -rf out/
