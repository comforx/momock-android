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
package com.momock.holder;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import com.momock.app.App;

public abstract class ViewHolder implements IComponentHolder{
	public static interface OnViewCreatedHandler
	{
		void onViewCreated(View view);
	}
	public abstract <T extends View> T getView();
	public abstract void reset();

	public static ViewHolder get(Fragment fragment)	{
		return get(fragment.getView());
	}

	public static ViewHolder get(Fragment fragment, int id)	{
		return get(fragment.getView(), id);
	}
	
	public static ViewHolder get(View view)	{
		final WeakReference<View> refView = new WeakReference<View>(view);
		return new ViewHolder()
		{

			@SuppressWarnings("unchecked")
			@Override
			public <T extends View> T getView() {
				return (T)refView.get();
			}

			@Override
			public void reset() {
			}
			
		};
	}

	public static ViewHolder get(View parentView, final int id)
	{
		final WeakReference<View> refView = new WeakReference<View>(parentView);
		return new ViewHolder()
		{
			WeakReference<View> refChild = null;
			@SuppressWarnings("unchecked")
			@Override
			public <T extends View> T getView() {
				if (refView.get() != null && refChild == null){
					refChild = new WeakReference<View>(refView.get().findViewById(id));
				}
				return (T)(refChild == null ? null : refChild.get());
			}

			@Override
			public void reset() {
				refChild = null;
			}
		};
	}

	public static ViewHolder get(View parentView, final String tag)
	{
		final WeakReference<View> refView = new WeakReference<View>(parentView);
		return new ViewHolder()
		{
			WeakReference<View> refChild = null;
			@SuppressWarnings("unchecked")
			@Override
			public <T extends View> T getView() {
				if (refView.get() != null && refChild == null){
					refChild = new WeakReference<View>(refView.get().findViewWithTag(tag));
				}
				return (T)(refChild == null ? null : refChild.get());
			}
			
			@Override
			public void reset() {
				refChild = null;
			}			
		};
	}

	public static ViewHolder get(int resourceId)
	{
		return get(resourceId, null);
	}
	public static ViewHolder get(final int resourceId, final OnViewCreatedHandler handler)
	{
		return new ViewHolder()
		{
			WeakReference<View> ref = null;
			@SuppressWarnings("unchecked")
			@Override
			public <T extends View> T getView() {
				if (ref == null || ref.get() == null)
				{
					LayoutInflater inflater = App.get().getLayoutInflater(App.get().getCurrentActivity());
					ref = new WeakReference<View>(inflater.inflate(resourceId, null));
					if (handler != null)
						handler.onViewCreated(ref.get());
				}
				return (T)ref.get();
			}
			
			@Override
			public void reset() {
				ref = null;
			}
		};
	}
	public static ViewHolder get(Context context, final int resourceId, final OnViewCreatedHandler handler)
	{
		final WeakReference<Context> refContext = new WeakReference<Context>(context);
		return new ViewHolder()
		{
			WeakReference<View> ref = null;
			@SuppressWarnings("unchecked")
			@Override
			public <T extends View> T getView() {				
				if (ref == null || ref.get() == null)
				{
					LayoutInflater inflater = App.get().getLayoutInflater(refContext.get());
					ref = new WeakReference<View>(inflater.inflate(resourceId, null));
					if (handler != null)
						handler.onViewCreated(ref.get());
				}
				return (T)ref.get();
			}
			
			@Override
			public void reset() {
				ref = null;
			}
		};
	}
}
