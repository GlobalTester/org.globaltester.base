package org.globaltester.core.renderer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.globaltester.core.resources.GtResourceHelper;
import org.globaltester.junit.JUnitHelper;
import org.globaltester.renderer.GtFopHelper;
import org.junit.Test;

public class GtFopHelperTest {
	@Test
	public void testGetFopFactory(){
		assertNotNull(GtFopHelper.getFopFactory());
	}
	
	@Test
	public void testTransformToPdf() throws IOException, CoreException{
		IProject project = JUnitHelper.createEmptyProject("testProject");
		GtResourceHelper.copyPluginFilesToWorkspaceProject("org.globaltester.core.test", project, "files/fopTestFiles", "input.xml", "test.xsl");
		Source src = new StreamSource(project.getFile("input.xml").getLocation().toFile());
		File destination = JUnitHelper.createTemporaryFile("dest");
		File stylesheet = project.getFile("test.xsl").getLocation().toFile();
		GtFopHelper.transformToPdf(src, destination, stylesheet);
		assertTrue(destination.length() > 0);
	}
}
