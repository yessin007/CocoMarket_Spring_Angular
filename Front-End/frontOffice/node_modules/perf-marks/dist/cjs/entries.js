"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.getEntriesByType = exports.getNavigationMarker = void 0;
var is_user_timing_api_supported_1 = require("./is-user-timing-api-supported");
var is_nodejs_env_1 = require("./is-nodejs-env");
/**
 * Gets the result for all marks that matches with the given mark name
 *
 * @param markName - Performance marker to be checked
 *
 * @returns PerfMarksPerformanceNavigationTiming[]
 *
 */
var getEntriesByType = function (entryName) {
    // NodeJS doesn't have support for getEntriesByType
    if (!is_user_timing_api_supported_1.isUserTimingAPISupported || is_nodejs_env_1.isNodeJSEnv) {
        return [];
    }
    return performance.getEntriesByType(entryName) || [];
};
exports.getEntriesByType = getEntriesByType;
/**
 * Gets the marks for `navigation` loaded mark
 *
 * @returns PerfMarksPerformanceNavigationTiming[]
 *
 */
var getNavigationMarker = function () { return getEntriesByType('navigation').pop() || {}; };
exports.getNavigationMarker = getNavigationMarker;
