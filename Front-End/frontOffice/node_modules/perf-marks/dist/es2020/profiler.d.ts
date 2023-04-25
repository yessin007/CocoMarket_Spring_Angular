import { PerfMarksPerformanceEntry } from './marks';
/**
 * profiler using User Timing Api method.
 * It will return a `PerfMarksPerformanceEntry` or
 * an PerfMarksPerformanceEntry inside `mark` key + the content for the callback method
 * If the given callback returns something
 *
 * usage:
 *
 * perfMarksProfile(
 *   () => { /** method content *\/}
 *   'name of the mark for this method',
 *   );
 *
 * @param {(() => any | Promise<any>)} callback
 * @param {string} name
 * @returns {Promise<PerfMarksPerformanceEntry>}
 */
declare const profiler: (callback: () => any | Promise<any>, name: string) => Promise<{
    data?: any;
    mark: PerfMarksPerformanceEntry;
}>;
export { profiler };
