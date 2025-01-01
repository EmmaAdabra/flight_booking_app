package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetSqlQueryUtil {
    public static String buildSqlQuery(String filePath) throws IOException {
        Path sqlQueryPath = Paths.get(filePath);
        String query = Files.readString(sqlQueryPath);

        query.replaceAll("\\s+", " ").trim();


//        StringBuilder query = new StringBuilder();
//
//        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
//            String line;
//
//            while ((line = br.readLine()) != null){
//                if(line.trim().startsWith("-- ")){
//                    continue;
//                }
//                query.append(line).append(" ");
//            }
//        }

        return query.toString().trim();
    }
}
