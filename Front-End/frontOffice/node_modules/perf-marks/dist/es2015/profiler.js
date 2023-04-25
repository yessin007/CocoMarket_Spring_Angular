import { __awaiter } from "tslib";
import { start, end } from './marks';
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
const profiler = (callback, name) => __awaiter(void 0, void 0, void 0, function* () {
    // Starting the marks
    start(name);
    try {
        const data = yield callback();
        // Finishing the marks and including `PerfMarksPerformanceEntry` on the response
        const mark = end(name);
        // Passing data as an object
        return !!data ? Object.assign({}, { data, mark }) : { mark };
    }
    catch (error) {
        end(name);
        throw error;
    }
});
export { profiler };
