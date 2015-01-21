package model;

/**
 * represents the Model's Layer of the Project's MVP architecture
 */

import java.util.Observer;

import model.domains.SearchDomain;
import tasks.Task;



public interface Model extends Task {

	void selectDomain(String args);
	void selectAlgorithm(String algorithmName);
	void solveDomain();
	Solution getSolution();
	void addObserver(Observer o);	
	void modelToObserver(String args);
	void stopThread();
	Thread getT();
	void setT(Thread t);
}
