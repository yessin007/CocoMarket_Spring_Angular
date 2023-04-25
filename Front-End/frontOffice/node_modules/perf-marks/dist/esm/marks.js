import { isUserTimingAPISupported } from './is-user-timing-api-supported';
import { isPerformanceObservableSupported } from './is-performance-observable-supported';
import { isNodeJSEnv } from './is-nodejs-env';
// Map() is not used in order to decrease the bundle
var marksMap = {};
var marksObserver = {};
/**
 * Get the current time based on User Timing API or Date
 *
 * @returns number
 *
 */
var getTimeNow = function () { return (isUserTimingAPISupported ? performance.now() : Date.now()); };
/**
 * Clear marks and measure of performance event
 *
 * @param markName - Performance marker to be checked
 *
 * @returns void
 *
 */
var clear = function (markName) {
    marksMap[markName] = undefined;
    // Removes PerformanceObserver references from memory
    if (!!marksObserver[markName]) {
        marksObserver[markName] = undefined;
    }
    if (!isUserTimingAPISupported) {
        return;
    }
    // Some versions of NodeJS doesn't support this method
    if (!isNodeJSEnv) {
        performance.clearMeasures(markName);
    }
    performance.clearMarks(markName);
};
/**
 * Start performance measure of event
 *
 * @param markName - Performance marker to be started
 *
 * @returns number
 *
 */
var start = function (markName) {
    if (isUserTimingAPISupported) {
        if (isNodeJSEnv && isPerformanceObservableSupported) {
            // eslint-disable-next-line compat/compat
            var obs_1 = new PerformanceObserver(function (list) {
                marksObserver[markName] = list.getEntries().find(function (f) { return f.name === markName; });
                obs_1.disconnect();
            });
            obs_1.observe({ entryTypes: ['measure'] });
        }
        performance.mark(markName);
    }
    marksMap[markName] = getTimeNow();
};
/**
 * Finishes performance measure of event and
 * clear marks and measure if applicable
 *
 * @param markName - Performance marker to be checked
 * @param markNameToCompare - Optional mark to compare to
 *
 * @returns PerfMarksPerformanceEntry
 *
 */
var end = function (markName, markNameToCompare) {
    try {
        var startTime = marksMap[markName];
        if (!isUserTimingAPISupported) {
            return startTime
                ? { duration: getTimeNow() - startTime, startTime: startTime, entryType: 'measure', name: markName }
                : {};
        }
        // If there's no User Timing mark to be compared with,
        // the package will create one to be used for better comparison
        if (!markNameToCompare) {
            performance.mark(markName + "-end");
        }
        performance.measure(markName, markName, markNameToCompare || markName + "-end");
        if (isNodeJSEnv) {
            if (!!marksObserver[markName]) {
                return marksObserver[markName];
            }
            return startTime
                ? { duration: getTimeNow() - startTime, startTime: startTime, entryType: 'measure', name: markName }
                : {};
        }
        var entry = performance.getEntriesByName(markName).pop();
        return entry || {};
    }
    catch (e) {
        // If previous mark was missing for some reason, this will throw.
        // This could only happen if something in event loop crashed
        // in an unexpected place earlier.
        // Don't pile on with more errors.
        return {};
    }
    finally {
        // Clear marks immediately to avoid growing buffer.
        clear(markName);
        // Clear marks used for comparison in case of it's value was passed
        // If the mark to compare is not passed, it should remove the one we create with `-end` suffix
        clear(markNameToCompare || markName + "-end");
    }
};
/**
 * Clear all marks and measures of performance event
 *
 * @returns void
 *
 */
var clearAll = function () {
    marksMap = {};
    marksObserver = {};
    if (!isUserTimingAPISupported) {
        return;
    }
    // Some versions of NodeJS doesn't support this method
    if (!isNodeJSEnv) {
        performance.clearMeasures();
    }
    performance.clearMarks();
};
export { start, end, clear, clearAll, isUserTimingAPISupported, isPerformanceObservableSupported };
