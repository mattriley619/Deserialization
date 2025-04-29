# Deserialization
Java program that deserializes a class and writes it to a file. Originally made to show that the file can be overwritten despite being deserialized.

To use:
  1. Run the program to create the serialized file.
  2. Comment out the serialization so it doesn't overwrite. (lines 22 - 36)
  3. Using a hex-editor, make some changes to the field values.
  4. Run the program again and you should see a hash mismatch error.

  5. UPDATE - Probably doesn't work becaue GitHub won't allow multiple files being named the same thing, which is necessary to modify the class.
