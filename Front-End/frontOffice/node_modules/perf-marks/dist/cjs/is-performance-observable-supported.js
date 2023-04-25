"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.isPerformanceObservableSupported = void 0;
/**
 * Boolean with the result of the check if PerformanceObservable
 * is supported for the current browser/NodeJS version
 *
 * @returns boolean
 *
 */
var isPerformanceObservableSupported = typeof PerformanceObserver !== 'undefined' &&
    // eslint-disable-next-line compat/compat
    typeof PerformanceObserver.prototype !== 'undefined' &&
    // eslint-disable-next-line compat/compat
    typeof PerformanceObserver.prototype.constructor === 'function';
exports.isPerformanceObservableSupported = isPerformanceObservableSupported;
