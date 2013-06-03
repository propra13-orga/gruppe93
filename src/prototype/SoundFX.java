/*
 * 
 * Based on Javazoom.net Sample Code
 * 
 * http://www.javazoom.net/mp3spi/documents.html
 * 
 */


package prototype;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class SoundFX implements Runnable
{	
	
	String filename;
	
	SoundFX(String filename)
	{
		this.filename = filename;
	}



	@Override
	public void run() {
		play(filename);
		
	}
	
	private static InputStream file;
	
	public void play(String filename)
	{
		
		try 
		{
			file = HintergrundMusik.class.getClassLoader().getResourceAsStream(filename); // File �ffnen
	    
			//MP3 anpassungen 
	   
			AudioInputStream in= AudioSystem.getAudioInputStream(file);
			AudioInputStream din = null;
			AudioFormat baseFormat = in.getFormat();
			AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,baseFormat.getSampleRate(),16,baseFormat.getChannels(),baseFormat.getChannels() * 2,baseFormat.getSampleRate(),false);
			din = AudioSystem.getAudioInputStream(decodedFormat, in);
			// Play now.
	   
			rawplay(decodedFormat, din);
			in.close();
	   
		} catch (Exception e){} 
	} 
	
	// Player
	
	private void rawplay(AudioFormat targetFormat, AudioInputStream din) throws IOException, LineUnavailableException
	{
		byte[] data = new byte[4096];
		SourceDataLine line = getLine(targetFormat); 
		if (line != null)
		{
	    
			// Start
			line.start();
			int nBytesRead = 0, nBytesWritten = 0;
			while (nBytesRead != -1)
			{
				nBytesRead = din.read(data, 0, data.length);
				if (nBytesRead != -1) nBytesWritten = line.write(data, 0, nBytesRead);
			}
			// Stop
			line.drain();
			line.stop();
			line.close();
			din.close();
		} 
	}

	private SourceDataLine getLine(AudioFormat audioFormat) throws LineUnavailableException
	{
		SourceDataLine res = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		res = (SourceDataLine) AudioSystem.getLine(info);
		res.open(audioFormat);
		return res;
	}

}
