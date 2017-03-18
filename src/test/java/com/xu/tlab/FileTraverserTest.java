package com.xu.tlab;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

import com.xlab.tlab.impl.FileTraverser;

/**
 * The class tests the methods of FileTraverser
 * @author XU
 *
 */
public class FileTraverserTest {

	public static void main( String[] args ) {
		FileTraverser fileTraverser = new FileTraverser();
		File file = new File( "/data" );

		// traverse file '/data' to get files named like this 'xxx.jpg'
		List<String> list = fileTraverser.traverseFile( file, new FilenameFilter() {

			public boolean accept( File dir, String name ) {
				return name.endsWith( ".jpg" );
			}
		} );

		// print the result
		for( String li : list ) {
			System.out.println( li );
		}
	}
}
