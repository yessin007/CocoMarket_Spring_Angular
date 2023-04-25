import { isUserTimingAPISupported } from './is-user-timing-api-supported';
import { isPerformanceObservableSupported } from './is-performance-observable-supported';
/**
 * Clear marks and measure of performance event
 *
 * @param markName - Performance marker to be checked
 *
 * @returns void
 *
 */
declare const clear: (markName: string) => void;
/**
 * Start performance measure of event
 *
 * @param markName - Performance marker to be started
 *
 * @returns number
 *
 */
declare const start: (markName: string) => void;
/**
 * Response type of `PerfMarks.end()` method
 *
 */
export declare type PerfMarksPerformanceEntry = PerformanceEntry | {
    duration?: number;
    startTime?: number;
    name?: string;
    entryType?: string;
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
declare const end: (markName: string, markNameToCompare?: string | undefined) => PerfMarksPerformanceEntry;
/**
 * Clear all marks and measures of performance event
 *
 * @returns void
 *
 */
declare const clearAll: () => void;
export { start, end, clear, clearAll, isUserTimingAPISupported, isPerformanceObservableSupported };
