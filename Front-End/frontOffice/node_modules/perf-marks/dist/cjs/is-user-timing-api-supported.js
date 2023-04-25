"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.isUserTimingAPISupported = void 0;
/**
 * Boolean with the result of the check if User Timing API
 * is supported for the current browser/NodeJS version
 *
 * @returns boolean
 *
 */
var isUserTimingAPISupported = typeof performance !== 'undefined' &&
    typeof performance.now !== 'undefined' &&
    typeof performance.mark === 'function' &&
    typeof performance.measure === 'function' &&
    (typeof performance.clearMarks === 'function' || typeof performance.clearMeasures === 'function');
exports.isUserTimingAPISupported = isUserTimingAPISupported;
