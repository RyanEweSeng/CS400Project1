// --== CS400 File Header Information ==--
// Name: Ethan McKellips
// Email: emckellips@wisc.edu
// Team: Red
// Group: IG
// TA: Sid
// Lecturer: Florian
// Notes to Grader: N/A

//Imports
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit class that tests the functionality of the GradeDataReader test
 * 
 * @author Ethan
 *
 */
class GradeDataReaderTest {
	// Instance variables
	GradeDataReader dataReader;
	FileReader fr = null;

	/**
	 * Method that is implemented before the test
	 */
	@BeforeEach
	void beforeEach() {
		File file = new File("C:\\Users\\Ethan\\eclipse-workspace\\CS400GradeProject\\src\\gradeData.csv");
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			System.out.println("This file was not found.");
			e.printStackTrace();
		}
		dataReader = new GradeDataReader();

	}

	/**
	 * Runs the readDataSet() method with the data set and make sure it compiles
	 * without errors
	 */
	@Test
	void compileTest() {
		if (fr != null) {
			try {
				dataReader.readDataSet(fr);
			} catch (FileNotFoundException e) {
				fail("The readDataSet() method caught a FileNotFoundException.");
				e.printStackTrace();
			} catch (IOException e) {
				fail("The readDataSet() method caught an IO Exception.");
				e.printStackTrace();
			} catch (DataFormatException e) {
				fail("The readDataSet() method caught a DataFormatException");
				e.printStackTrace();
			}
		} else {
			fail("The readDataSet() is empty.");
		}

	}

	/**
	 * Tests the data reader to make sure that the each student was added properly
	 * by searching for the first and last element
	 */
	@Test
	void FirstLast() {
		List<GradeInterface> data = null;
		try {
			data = dataReader.readDataSet(fr);
		} catch (FileNotFoundException e) {
			fail("The readDataSet() method caught a FileNotFoundException.");
			e.printStackTrace();
		} catch (IOException e) {
			fail("The readDataSet() method caught an IO Exception.");
			e.printStackTrace();
		} catch (DataFormatException e) {
			fail("The readDataSet() method caught a DataFormatException");
			e.printStackTrace();
		}

		List<String> firstGradeName = new ArrayList<String>();
		firstGradeName.add("Bryanna");
		firstGradeName.add("Plaisir");
		Grade first = new Grade(firstGradeName, 76);
		assertEquals(first.toString(), data.get(0).toString());

		List<String> lastGradeName = new ArrayList<String>();
		lastGradeName.add("Dragan");
		lastGradeName.add("Malone");
		Grade last = new Grade(lastGradeName, 23);
		assertEquals(last.toString(), data.get(data.size() - 1).toString());

	}

}