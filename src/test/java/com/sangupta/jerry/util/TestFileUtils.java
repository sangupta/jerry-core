package com.sangupta.jerry.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import com.sangupta.jerry.constants.SystemPropertyNames;

public class TestFileUtils {

	@Test
	public void testUserHome() {
		File file = new File(System.getProperty(SystemPropertyNames.USER_HOME));
		File toTest = FileUtils.getUsersHomeDirectory();
		
		if(!file.getAbsolutePath().equals(toTest.getAbsolutePath())) {
			Assert.assertFalse("User home directory is not correct", false);
		}
	}
	
	@Test
	public void testUserWorkDir() {
		File file = new File(System.getProperty(SystemPropertyNames.USER_DIR));
		File toTest = FileUtils.getUsersHomeDirectory();
		
		if(!file.getAbsolutePath().equals(toTest.getAbsolutePath())) {
			Assert.assertFalse("User home directory is not correct", false);
		}
	}
	
	@Test
	public void testIsAbsolutePath() {
		Assert.assertFalse(FileUtils.isAbsolutePath("file.txt"));
		Assert.assertFalse(FileUtils.isAbsolutePath("./../file.txt"));
		Assert.assertFalse(FileUtils.isAbsolutePath("./file.txt"));
		Assert.assertFalse(FileUtils.isAbsolutePath("test/file.txt"));
		
		Assert.assertTrue(FileUtils.isAbsolutePath("/file.txt"));
		Assert.assertTrue(FileUtils.isAbsolutePath("c:/file.txt"));
		Assert.assertTrue(FileUtils.isAbsolutePath("~/file.txt"));
		Assert.assertFalse(FileUtils.isAbsolutePath("./../~/file.txt"));
	}
	
	@Test
	public void listFiles() {
		// create a set of files and folders on disk to test
		File base = setupForListFiles();
		
		// run all tests
		final String basePath = base.getAbsolutePath();
		
		List<File> files = FileUtils.listFiles(basePath);
		Assert.assertNotNull(files);
		Assert.assertFalse(files.isEmpty());
		Assert.assertEquals(3, files.size());
		assertContains(files, base, "test1.txt");
		assertContains(files, base, "test2.txt");
		assertContains(files, base, "test33.pdf");
		
		// only * on extension
		files = FileUtils.listFiles(basePath + File.separator + "test1.*");
		Assert.assertNotNull(files);
		Assert.assertFalse(files.isEmpty());
		Assert.assertEquals(1, files.size());
		assertContains(files, base, "test1.txt");
		
		// with ?
		files = FileUtils.listFiles(basePath + File.separator + "test?.*");
		Assert.assertNotNull(files);
		Assert.assertFalse(files.isEmpty());
		Assert.assertEquals(2, files.size());
		assertContains(files, base, "test1.txt");
		assertContains(files, base, "test2.txt");

		// with *.txt
		files = FileUtils.listFiles(basePath + File.separator + "test*.txt");
		Assert.assertNotNull(files);
		Assert.assertFalse(files.isEmpty());
		Assert.assertEquals(2, files.size());
		assertContains(files, base, "test1.txt");
		assertContains(files, base, "test2.txt");
		
		// all
		files = FileUtils.listFiles(basePath + File.separator + "test*.*");
		Assert.assertNotNull(files);
		Assert.assertFalse(files.isEmpty());
		Assert.assertEquals(3, files.size());
		assertContains(files, base, "test1.txt");
		assertContains(files, base, "test2.txt");
		assertContains(files, base, "test33.pdf");
		
		// with ??
		files = FileUtils.listFiles(basePath + File.separator + "test??.*");
		Assert.assertNotNull(files);
		Assert.assertFalse(files.isEmpty());
		Assert.assertEquals(1, files.size());
		assertContains(files, base, "test33.pdf");
		
		// clean up
		try {
			org.apache.commons.io.FileUtils.cleanDirectory(base);
			org.apache.commons.io.FileUtils.deleteQuietly(base);
		} catch (IOException e) {
		}
	}
	
	@Test
	public void listFilesRecursive() {
		// create a set of files and folders on disk to test
		File base = setupForListFiles();
		
		// run all tests
		final String basePath = base.getAbsolutePath();
		
		List<File> files = FileUtils.listFiles(basePath, true);
		Assert.assertNotNull(files);
		Assert.assertFalse(files.isEmpty());
		Assert.assertEquals(14, files.size());
		assertContains(files, base, "test1.txt");
		assertContains(files, base, "test2.txt");
		assertContains(files, base, "test33.pdf");
		assertContains(files, base, "one/test1.txt");
		assertContains(files, base, "one/test1.jpg");
		assertContains(files, base, "one/test2.jpg");
		assertContains(files, base, "one/test33.jpg");
		assertContains(files, base, "one/test1.png");
		assertContains(files, base, "one/test2.png");
		assertContains(files, base, "one/test33.png");
		assertContains(files, base, "two/test1.pdf");
		assertContains(files, base, "two/test1.gif");
		assertContains(files, base, "two/test1.txt");
		assertContains(files, base, "two/three/test1.txt");
		
		// only * on extension
		files = FileUtils.listFiles(basePath + File.separator + "test1.*", true);
		Assert.assertNotNull(files);
		Assert.assertFalse(files.isEmpty());
		Assert.assertEquals(8, files.size());
		assertContains(files, base, "test1.txt");
		assertContains(files, base, "one/test1.txt");
		assertContains(files, base, "one/test1.jpg");
		assertContains(files, base, "one/test1.png");
		assertContains(files, base, "two/test1.pdf");
		assertContains(files, base, "two/test1.gif");
		assertContains(files, base, "two/test1.txt");
		assertContains(files, base, "two/three/test1.txt");
		
		// with ?
		files = FileUtils.listFiles(basePath + File.separator + "test?.*", true);
		Assert.assertNotNull(files);
		Assert.assertFalse(files.isEmpty());
		Assert.assertEquals(11, files.size());
		assertContains(files, base, "test1.txt");
		assertContains(files, base, "test2.txt");
		assertContains(files, base, "one/test1.txt");
		assertContains(files, base, "one/test1.jpg");
		assertContains(files, base, "one/test1.png");
		assertContains(files, base, "one/test2.jpg");
		assertContains(files, base, "one/test2.png");
		assertContains(files, base, "two/test1.pdf");
		assertContains(files, base, "two/test1.gif");
		assertContains(files, base, "two/test1.txt");
		assertContains(files, base, "two/three/test1.txt");
		
		// with *.txt
		files = FileUtils.listFiles(basePath + File.separator + "test*.txt", true);
		Assert.assertNotNull(files);
		Assert.assertFalse(files.isEmpty());
		Assert.assertEquals(5, files.size());
		assertContains(files, base, "test1.txt");
		assertContains(files, base, "one/test1.txt");
		assertContains(files, base, "two/test1.txt");
		assertContains(files, base, "two/three/test1.txt");
		
		// all
		files = FileUtils.listFiles(basePath + File.separator + "test*.*", true);
		Assert.assertNotNull(files);
		Assert.assertFalse(files.isEmpty());
		Assert.assertEquals(14, files.size());
		assertContains(files, base, "test1.txt");
		assertContains(files, base, "test2.txt");
		assertContains(files, base, "test33.pdf");
		assertContains(files, base, "one/test1.txt");
		assertContains(files, base, "one/test1.jpg");
		assertContains(files, base, "one/test2.jpg");
		assertContains(files, base, "one/test33.jpg");
		assertContains(files, base, "one/test1.png");
		assertContains(files, base, "one/test2.png");
		assertContains(files, base, "one/test33.png");
		assertContains(files, base, "two/test1.pdf");
		assertContains(files, base, "two/test1.gif");
		assertContains(files, base, "two/test1.txt");
		assertContains(files, base, "two/three/test1.txt");
		
		// with ??
		files = FileUtils.listFiles(basePath + File.separator + "test??.*", true);
		Assert.assertNotNull(files);
		Assert.assertFalse(files.isEmpty());
		Assert.assertEquals(3, files.size());
		assertContains(files, base, "test33.pdf");
		assertContains(files, base, "one/test33.jpg");
		assertContains(files, base, "one/test33.png");
		
		// clean up
		try {
			org.apache.commons.io.FileUtils.cleanDirectory(base);
			org.apache.commons.io.FileUtils.deleteQuietly(base);
		} catch (IOException e) {
		}
	}

	@Test
	public void testGetExtensionFile() {
		Assert.assertEquals("png", FileUtils.getExtension(new File("temp.png")));
		Assert.assertEquals("txt", FileUtils.getExtension(new File("temp.txt")));
		Assert.assertEquals("", FileUtils.getExtension(new File("temp")));
	}
	
	@Test
	public void testGetExtension() {
		Assert.assertEquals(null, FileUtils.getExtension(""));
		Assert.assertEquals(null, FileUtils.getExtension((String) null));
		Assert.assertEquals(null, FileUtils.getExtension("test"));
		Assert.assertEquals(null, FileUtils.getExtension("test."));
		Assert.assertEquals("png", FileUtils.getExtension("temp.png"));
		Assert.assertEquals("png", FileUtils.getExtension("temp.test.png"));
		Assert.assertEquals("png", FileUtils.getExtension("temp.another.one.png"));
	}
	
	protected void assertContains(List<File> files, File base, String path) {
		path = base.getAbsolutePath() + File.separator + path;
		path = path.replace('\\', '/');
		for(File file : files) {
			String loc = file.getPath();
			loc = loc.replace('\\', '/');
			if(loc.equals(path)) {
				return;
			}
		}
		
		Assert.assertTrue("Missing file " + path, false);
	}
	
	protected File setupForListFiles() {
		File workDir = FileUtils.getUsersWorkingDirectory();
		File base = new File(workDir, UUID.randomUUID().toString());
		base.mkdirs();
		
		// create two sub directories
		File one = new File(base, "one");
		one.mkdirs();
		File two = new File(base, "two");
		two.mkdirs();
		
		File three = new File(two, "three");
		three.mkdirs();
		
		// start creating files
		createFile(new File(base, "test1.txt"), "123");
		createFile(new File(base, "test2.txt"), "123");
		createFile(new File(base, "test33.pdf"), "123");
		
		createFile(new File(one, "test1.txt"), "123");
		createFile(new File(one, "test1.jpg"), "123");
		createFile(new File(one, "test2.jpg"), "123");
		createFile(new File(one, "test33.jpg"), "123");
		createFile(new File(one, "test1.png"), "123");
		createFile(new File(one, "test2.png"), "123");
		createFile(new File(one, "test33.png"), "123");
		
		createFile(new File(two, "test1.pdf"), "123");
		createFile(new File(two, "test1.gif"), "123");
		createFile(new File(two, "test1.txt"), "123");
		
		createFile(new File(three, "test1.txt"), "123");
		
		// return base
		return base;
	}
	
	protected void createFile(File file, String data) {
		try {
			org.apache.commons.io.FileUtils.write(file, data);
		} catch (IOException e) {
			Assert.assertFalse("File creation for test data failed", false);
		}
	}
}
