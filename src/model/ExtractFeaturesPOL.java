/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Jama.Matrix;
import java.awt.image.BufferedImage;

/**
 *
 * @author auswise
 */
public class ExtractFeaturesPOL extends ExtractFeaturesABC
{
    public Matrix extract(BufferedImage bimg)
    {
        this.bimg=bimg;
        
       obliczMaxIJ();
        
        Matrix fi = new Matrix(2,1);
        fi.set(0, 0, cechaI(0.7,1.0));
        fi.set(1, 0, cechaJ(0.7,1.0));
        
        return fi;
        
    }
}
