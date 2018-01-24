/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cyber;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author cen
 */
public class textmerge {

    public static void main(String args[]) throws FileNotFoundException, IOException {
        String sCurrentLine;
        String sCurrentLine1;
        BufferedReader br;
        ArrayList aw = new ArrayList();

        BufferedReader br1;
        ArrayList aw1 = new ArrayList();

        br = new BufferedReader(new FileReader("rt-polarity.pos"));

        while ((sCurrentLine = br.readLine()) != null) {
            aw.add(sCurrentLine);
        }

        br1 = new BufferedReader(new FileReader("rt-polarity.neg"));

        while ((sCurrentLine1 = br1.readLine()) != null) {
            aw1.add(sCurrentLine1);
        }

        File file = new File("polarity.txt");

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        for (int i = 0; i < aw.size(); i++) {
            bw.write((String) aw.get(i));
            bw.newLine();
            bw.write((String) aw1.get(i));
            bw.newLine();
        }

        File file1 = new File("classlabel.txt");

        FileWriter fw1 = new FileWriter(file1.getAbsoluteFile());
        BufferedWriter bw1 = new BufferedWriter(fw1);

        for (int i = 0; i < 10621; i++) {
            if (i % 2 == 0) {
                bw1.write("1");
                bw1.newLine();
                System.out.println("f");
            } else {
                bw1.write("0");
                bw1.newLine();
            }
        }
        bw.close();
        bw1.close();

    }

}
