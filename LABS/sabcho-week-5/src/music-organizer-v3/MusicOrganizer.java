import java.util.ArrayList;

/**
 * A class to hold details of audio files.
 * 
 * @author David J. Barnes and Michael Kölling
 *
 * Modified by Simon Larsén 2017.09.23
 *
 * @version 2017.09.23
 */
public class MusicOrganizer
{
    // An ArrayList for storing the file names of music files.
    private ArrayList<String> files;
    // A player for the music files.
    private MusicPlayer player;

    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer()
    {
        files = new ArrayList<>();
        player = new MusicPlayer();
    }

    /**
     * Add a file to the collection.
     * @param filename The file to be added.
     */
    public void addFile(String filename)
    {
        files.add(filename);
    }

    /**
     * Return the number of files in the collection.
     * @return The number of files in the collection.
     */
    public int getNumberOfFiles()
    {
        return files.size();
    }

    /**
     * List a file from the collection.
     * @param index The index of the file to be listed.
     */
    public void listFile(int index)
    {
        if(validIndex(index)) {
            String filename = files.get(index);
            System.out.println(filename);
        }
    }

    /**
     * Show a list of all the files in the collection.
     */
    public void listAllFilesV1()
    {
        for(String filename : files) {
            System.out.println(filename);
        }
    }
    
    //Answer to Exercise 4.24
    
    public void listAllFiles()
    {
        int position=0;
        for(String filename : files) {
            position = position + 1;
            System.out.println(position + ": " + filename);
        }
    }

    /**
     * Remove a file from the collection.
     * @param index The index of the file to be removed.
     */
    public void removeFile(int index)
    {
        if(validIndex(index)) {
            files.remove(index);
        }
    }

    /**
     * Start playing a file in the collection.
     * Use stopPlaying() to stop it playing.
     * @param index The index of the file to be played.
     */
    public void startPlaying(int index)
    {
        if(validIndex(index)) {
            String filename = files.get(index);
            player.startPlaying(filename);
        }
    }

    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        player.stop();
    }

    /**
     * Play a file in the collection. Only return once playing has finished.
     * @param index The index of the file to be played.
     */
    
    
    public void playAndWait(int index)
    {
        
        if(validIndex(index)) {
            
            String filename = files.get(index);
            player.playSample(filename);
            
        }
        }
       
        
    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean validIndex(int index)
    {
        // The return value.
        // Set according to whether the index is valid or not.
        boolean valid;

        if(index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if(index >= files.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }

    /**
     * List the names of files matching the given search string.
     * @param searchString The string to match.
     */
    
    //Answer to Exercise 4.26
public void listMatching(String searchString)
    {
        boolean fileTest = false;
        for(String filename : files) {
            if(filename.contains(searchString)) {
                // A match.
                fileTest = true;
            }
        }
        
        if (fileTest==false)
        {
            System.out.println("No match");
        }
        
    }
    
    //Answer for 4.27
   public void playCertainArtist(String artist)
    { 
       
        for(String filename : files) {
            if(filename.contains(artist)) {
                // A match.
                
                player.playSample(filename);
            }
            
        }
        System.out.println("h");
        
       
    }
}
