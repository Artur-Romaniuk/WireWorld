package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

   public abstract class WWIO {
        public static List<String> readFileAndSaveToTerminal(File file) throws IOException {
            List<String> records = new ArrayList<String>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null)
            {
                records.add(line);
            }
            reader.close();
            return records;
        }
    }
