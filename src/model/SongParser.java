package model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.ID3Tags;
import org.apache.tika.parser.mp3.ID3v2Frame;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SongParser {

    public static void main(String[] args) {
        String fileLocation = "C:/Users/jeffc/Desktop/To Do/SWDESPA-DC2/src/audio/Mortal Reminder.mp3";

        try {
            Database DB = new Database();
            SongService service = new SongService(DB);

            File folder = new File("./src/audio");
            File[] listOfFiles = folder.listFiles();

            if (listOfFiles != null) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile()) {

                        InputStream input = new FileInputStream(new File("./src/audio/"+listOfFiles[i].getName()));
                        ContentHandler handler = new DefaultHandler();
                        Metadata metadata = new Metadata();
                        Parser parser = new Mp3Parser();
                        ParseContext parseCtx = new ParseContext();
                        parser.parse(input, handler, metadata, parseCtx);
                        input.close();

                        // List all metadata
                        String[] metadataNames = metadata.names();

                        for(String name : metadataNames){
                            System.out.println(name + ": " + metadata.get(name));
                        }

                        System.out.println("File " + listOfFiles[i].getName());
                        try {
                            Song song = new Song();
                            song.setSongID(service.getAll().size() + 1);
                            song.setFilename(metadata.get("title")+".mp3");
                            song.setSongTitle(metadata.get("title"));
                            song.setArtist(metadata.get("xmpDM:artist"));
                            song.setAlbum(metadata.get("xmpDM:album"));
                            song.setGenre(metadata.get("xmpDM:genre"));
                            song.setYear(Integer.parseInt(metadata.get("xmpDM:releaseDate")));
                            float songDuration = ((Float.parseFloat(metadata.get("xmpDM:duration"))/1000)/60);
                            System.out.println(songDuration);
                            song.setDuration(songDuration);
                            service.add(song);
                        }catch (Exception e){

                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }
    }
}
