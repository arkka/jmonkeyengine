/*
 * Copyright (c) 2009-2013 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.jme3.scene;

import com.jme3.asset.AssetKey;
import com.jme3.asset.CloneableSmartAsset;
import com.jme3.bounding.BoundingVolume;
import com.jme3.collision.Collidable;
import com.jme3.export.*;
import com.jme3.light.Light;
import com.jme3.light.LightList;
import com.jme3.material.Material;
import com.jme3.math.*;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.control.Control;
import com.jme3.util.SafeArrayList;
import com.jme3.util.TempVars;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
public abstract class LightOperations extends Spatial
{
    protected LightList localLights;
    protected transient LightList worldLights;

    /**
     * Returns the local {@link LightList}, which are the lights
     * that were directly attached to this <code>Spatial</code> through the
     * {@link #addLight(com.jme3.light.Light) } and 
     * {@link #removeLight(com.jme3.light.Light) } methods.
     * 
     * @return The local light list
     */
    public LightList getLocalLightList() {
        return localLights;
    }

    /**
     * Returns the world {@link LightList}, containing the lights
     * combined from all this <code>Spatial's</code> parents up to and including
     * this <code>Spatial</code>'s lights.
     * 
     * @return The combined world light list
     */
    public LightList getWorldLightList() {
        return worldLights;
    }


    /**
     * <code>addLight</code> adds the given light to the Spatial; causing
 all
     * child Spatials to be affected by it.
     *
     * @param light The light to add.
     */
    public void addLight(Light light) {
        localLights.add(light);
        setLightListRefresh();
    }

    /**
     * <code>removeLight</code> removes the given light from the Spatial.
     * 
     * @param light The light to remove.
     * @see Spatial#addLight(com.jme3.light.Light) 
     */
    public void removeLight(Light light) {
        localLights.remove(light);
        setLightListRefresh();
    }
}

