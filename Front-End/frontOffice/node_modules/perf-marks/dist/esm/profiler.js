import { __awaiter, __generator } from "tslib";
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
var profiler = function (callback, name) { return __awaiter(void 0, void 0, void 0, function () {
    var data, mark, error_1;
    return __generator(this, function (_a) {
        switch (_a.label) {
            case 0:
                // Starting the marks
                start(name);
                _a.label = 1;
            case 1:
                _a.trys.push([1, 3, , 4]);
                return [4 /*yield*/, callback()];
            case 2:
                data = _a.sent();
                mark = end(name);
                // Passing data as an object
                return [2 /*return*/, !!data ? Object.assign({}, { data: data, mark: mark }) : { mark: mark }];
            case 3:
                error_1 = _a.sent();
                end(name);
                throw error_1;
            case 4: return [2 /*return*/];
        }
    });
}); };
export { profiler };
