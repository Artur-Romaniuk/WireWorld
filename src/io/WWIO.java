package io;

import logic.WWElementGroup;
import logic.elements.WWElement;

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

        public static void saveTerminalToFile(File file, String terminalText) throws  IOException{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(terminalText);
            writer.close();
        }

        public static void saveIterationToFile(File file, WWElementGroup wwElementGroup) throws  IOException{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("");
            for (WWElement element:wwElementGroup.getElemList()) {
                writer.append(element.toString()+"\n");
            }
            for (WWElement element:wwElementGroup.getElectronHeadList()) {
                writer.append(element.toString()+"\n");
            }
            for (WWElement element:wwElementGroup.getElectronTailList()) {
                writer.append(element.toString()+"\n");
            }
            writer.close();
        }

    }
