/*******************************************************************************
 * Copyright (c) 2006-2018 Massachusetts General Hospital 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. I2b2 is also distributed under
 * the terms of the Healthcare Disclaimer.
 ******************************************************************************/
/*

 * 
 * Contributors:
 * 		Raj Kuttan
 * 		Lori Phillips
 */
package edu.harvard.i2b2.im.util;


/**
 * Define JAXB constants here.
 * For dynamic configuration, move these values to property file
 * and read from it.
 *
 * @author rkuttan
 */
public class JAXBConstant {
    public static final String[] DEFAULT_PACKAGE_NAME = new String[] {
            "edu.harvard.i2b2.im.datavo.i2b2message",
            "edu.harvard.i2b2.im.datavo.i2b2result",
            "edu.harvard.i2b2.im.datavo.pdo",
            "edu.harvard.i2b2.im.datavo.pm",
            "edu.harvard.i2b2.im.datavo.wdo",
            "edu.harvard.i2b2.im.datavo.pdo.query",
            "edu.harvard.i2b2.im.datavo.setfinder.query"
        };
}
