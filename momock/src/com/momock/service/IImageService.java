/*******************************************************************************
 * Copyright 2012 momock.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.momock.service;

import android.graphics.Bitmap;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.momock.holder.ImageHolder;

public interface IImageService extends IService{

	public static interface ImageSetter{
		void setImage(Bitmap bitmap);
	}
	
	void load(ImageHolder holder, ImageSetter setter);
	
	void load(BaseAdapter adapter, ImageView view, String url);
	
	void load(ImageView view, String url);
}
