# Deserialization
Java program that deserializes a class and writes it to a file. Originally made to show that the file can be overwritten despite being deserialized.

To use:
  1. Run the program to create the serialized file.
  2. Comment out the write to file and create and store hash sections so they don't overwrite. (lines 20 - 36)
  3. Using a hex-editor, make some changes to the field values.
  4. Run the program again and you should see a hash mismatch error.
