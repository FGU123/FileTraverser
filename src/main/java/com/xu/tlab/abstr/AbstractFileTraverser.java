package com.xu.tlab.abstr;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author XU
 * <pre><b>email: </b>forgooduser@163.com</pre> 
 */
public abstract class AbstractFileTraverser {

	/**
	 * The method to traverse file to get file paths
	 */
	public List<String> traverseFile( File root ) {

		List<String> paths = new ArrayList<String>();

		LinkedList<String> linkedList = new LinkedList<String>();
		String rootPath = root.getAbsolutePath();
		linkedList.add( rootPath );
		paths.add( rootPath );
		handleRoot( rootPath );

		while( linkedList.size() > 0 ) {
			String path = linkedList.removeFirst();

			File file = new File( path );
			File[] subFiles = file.listFiles();

			if( null == subFiles || subFiles.length == 0 ) {
				continue;
			}

			for( File f : subFiles ) {
				String absolutePath = f.getAbsolutePath();
				paths.add( absolutePath );
				if( f.isDirectory() ) {
					linkedList.add( absolutePath );
					handleDirectory( absolutePath );
					continue;
				}
				handleFile( absolutePath );
			}
		}

		return paths;
	}

	public List<String> traverseFile( File root, FilenameFilter filter ) {

		List<String> paths = new ArrayList<String>();

		LinkedList<String> linkedList = new LinkedList<String>();
		String rootPath = root.getAbsolutePath();
		linkedList.add( rootPath );
		if( filter.accept( root, root.getName() ) ) {
			paths.add( rootPath );
		}

		while( linkedList.size() > 0 ) {
			String path = linkedList.removeFirst();

			File file = new File( path );
			File[] subFiles = file.listFiles();

			if( null == subFiles || subFiles.length == 0 ) {
				continue;
			}

			for( File f : subFiles ) {
				String absolutePath = f.getAbsolutePath();
				if( filter.accept( f, f.getName() ) ) {
					paths.add( absolutePath );
				}
				if( f.isDirectory() ) {
					linkedList.add( absolutePath );
					continue;
				}
			}
		}

		return paths;
	}

	/**
	 * to handle something about the root path
	 */
	protected abstract void handleRoot( String rootPath );

	/**
	 * to handle something about the file path
	 */
	protected abstract void handleFile( String absolutePath );

	/**
	 * to handle something about the directory path
	 */
	protected abstract void handleDirectory( String absolutePath );

}
