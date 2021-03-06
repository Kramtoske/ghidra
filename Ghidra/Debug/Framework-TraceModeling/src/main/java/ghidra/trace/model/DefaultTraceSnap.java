/* ###
 * IP: GHIDRA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ghidra.trace.model;

import java.util.Objects;

/**
 * NOTE: This is used to mark <trace,snap>; regardless of whether that snapshot is actually in the
 * database.... Cannot just use TraceSnapshot here.
 */
public class DefaultTraceSnap implements TraceSnap {

	private final Trace trace;
	private final long snap;

	public DefaultTraceSnap(Trace trace, long snap) {
		this.trace = trace;
		this.snap = snap;
	}

	@Override
	public Trace getTrace() {
		return trace;
	}

	@Override
	public long getSnap() {
		return snap;
	}

	@Override
	public String toString() {
		return "TraceSnap<" + trace + ": " + snap + ">";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DefaultTraceSnap)) {
			return false;
		}
		DefaultTraceSnap that = (DefaultTraceSnap) obj;
		if (this.trace != that.trace) {
			return false;
		}
		if (this.snap != that.snap) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(trace, snap);
	}

	@Override
	public int compareTo(TraceSnap that) {
		if (this == that) {
			return 0;
		}
		int result;
		result = this.trace.getName().compareTo(that.getTrace().getName());
		if (result != 0) {
			return result;
		}
		result = Long.compareUnsigned(this.snap, that.getSnap());
		if (result != 0) {
			return result;
		}
		return 0;
	}
}
