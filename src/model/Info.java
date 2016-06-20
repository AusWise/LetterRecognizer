package model;

import Jama.Matrix;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author auswise
 */
public class Info 
{
    public static final Info INSTANCE = new Info(); 
   
    int size=0;
    List<Character> Y;
    int selectedModel=0;
    //String path;
    //int chosenLetter; 
    
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    
    public Model m = new ModelABC();
    
    private Info()
    {
        this.Y = Arrays.asList(m.Y);
//        chosenLetter=Y.get(0);
    }    
    
    public int getSize()
    {
        return size;
    }
    
    public Model getModel()
    {
        return m;
    }
    
    public void setModel(Model model)
    {
        List<Character> oldY = Y;
        m=model;
        this.Y = Arrays.asList(m.Y);
        changeSupport.firePropertyChange("Y", oldY, Y);
        int old = size;
        size=m.D.D.size();
        changeSupport.firePropertyChange("size", old, size);
    }
    
    public List<Character> getY()
    {
        return Y;
    }
    
//    public int getChosenLetter()
//    {
//        return chosenLetter;
//    }
//    
//    public void setChosenLetter(int chosenLetter)
//    {
//        this.chosenLetter=chosenLetter;
//    }
    
//    public void setPath(String path)
//    {
//        this.path=path;
//    }
    
    public void addExampleToTheLearningSeries(Matrix features,int k)
    {
        m.D.addExample(features, k);
        int old = size;
        size=m.D.D.size();
        changeSupport.firePropertyChange("size", old, size);
    }
    
    public void loadExamplesToTheLearningSeries(String path) throws FileNotFoundException
    {
        m.D.load(path);
        int old = size;
        size=m.D.D.size();
        changeSupport.firePropertyChange("size", old, size);
    }
    
    public int getSelectedModel()
    {
        return selectedModel;
    }
    
    public void setSelectedModel(int selectedModel)
    {
        this.selectedModel=selectedModel;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }
 
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
