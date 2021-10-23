package com.encentral.image.inverter.impl;

import com.encentral.image.inverter.api.IImageInverter;
import com.google.inject.AbstractModule;

/**
 * @author EZIHE S. DANIEL
 * CreatedAt: 23/10/2021
 */
public class ImageInverterModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IImageInverter.class).to(DefaultImageInverterImpl.class);
    }
}
