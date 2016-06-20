package model;


import Jama.Matrix;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author auswise
 */
public class LearningSeries
{
    public List<Pair<Matrix,Integer>> D;
    int f;
    
    public LearningSeries()
    {
        D = new ArrayList<Pair<Matrix,Integer>>();
    }
    
    public void addExample(Matrix features, int k)
    {
        D.add(new Pair(features,k));
    }
    
    public void save(String path) throws FileNotFoundException
    {
        PrintWriter out = new PrintWriter(path);
        
        Pair<Matrix,Integer> e;
        
        Iterator<Pair<Matrix,Integer>> i = D.iterator();
        while(i.hasNext())
        {
            e = i.next();
            
            out.print(e.b);
            out.print(' ');
            
            for(int j=0;j<e.a.getRowDimension();j++)
            {
                out.print(e.a.get(j, 0));
                out.print(' ');
            }
            
            out.println();
        }
        
        out.close();
        
      
        
    }
    
    public void load(String path) throws FileNotFoundException
    {
        D.clear();
        
        Scanner sc = new Scanner(new File(path));
        
        Matrix a;
        int b;
        
        while(sc.hasNext())
        {
            b=sc.nextInt();
            a  = new Matrix(2,1);
            a.set(0, 0, sc.nextDouble());
            a.set(1, 0, sc.nextDouble());
            
            addExample(a,b);
        }
        
        
        
    }
}
