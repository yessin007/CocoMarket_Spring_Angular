import { isUserTimingAPISupported } from './is-user-timing-api-supported';
import { isNodeJSEnv } from './is-nodejs-env';
/**
 * Gets the result for all marks that matches with the given mark name
 *
 * @param markName - Performance marker to be checked
 *
 * @returns PerfMarksPerformanceNavigationTiming[]
 *
 */
const getEntriesByType = (entryName) => {
    // NodeJS doesn't have support for getEntriesByType
    if (!isUserTimingAPISupported || isNodeJSEnv) {
        return [];
    }
    return performance.getEntriesByType(entryName) || [];
};
/**
 * Gets the marks for `navigation` loaded mark
 *
 * @returns PerfMarksPerformanceNavigationTiming[]
 *
 */
const getNavigationMarker = () => getEntriesByType('navigation').pop() || {};
export { getNavigationMarker, getEntriesByType };
