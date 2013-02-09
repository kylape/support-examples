/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright
 * to this software to the public domain worldwide, pursuant to the CC0 Public
 * Domain Dedication. This software is distributed without any warranty.  
 * See <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package com.jboss.examples.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for helloReturn complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="helloReturn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="excited" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="greeting" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "helloReturn", propOrder = {
    "excited",
    "greeting"
})
public class HelloReturn {

    protected boolean excited;
    protected String greeting;

    /**
     * Gets the value of the excited property.
     * 
     */
    public boolean isExcited() {
        return excited;
    }

    /**
     * Sets the value of the excited property.
     * 
     */
    public void setExcited(boolean value) {
        this.excited = value;
    }

    /**
     * Gets the value of the greeting property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGreeting() {
        return greeting;
    }

    /**
     * Sets the value of the greeting property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGreeting(String value) {
        this.greeting = value;
    }

}
