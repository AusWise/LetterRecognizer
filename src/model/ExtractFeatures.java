package model;


import Jama.Matrix;
import java.awt.Color;
import java.awt.image.BufferedImage;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author auswise
 */
public interface ExtractFeatures 
{  
    public Matrix extract(BufferedImage bimg);
}
