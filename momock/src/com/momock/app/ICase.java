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
package com.momock.app;

import com.momock.outlet.IOutlet;
import com.momock.outlet.IPlug;

public interface ICase<A> {
	String getName();
	
	String getFullName();
	
	void onCreate();
	
	ICase<?> getParent();

	ICase<?> getCase(String name);

	void addCase(ICase<?> kase);

	void removeCase(String name);

	<P extends IPlug, H, T extends IOutlet<P, H>> T getOutlet(String name);

	<P extends IPlug, H, T extends IOutlet<P, H>> void addOutlet(String name, T outlet);

	void removeOutlet(String name);

	void run(Object... args);

	ICase<?> getActiveCase();

	void setActiveCase(ICase<?> kase);

	void onActivate();

	void onDeactivate();

	Object getAttachedObject();

	void attach(A target);

	void detach();

	void onAttach(A target);

	void onDetach(A target);
}
