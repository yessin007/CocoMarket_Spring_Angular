/**
 * Response type of `PerfMarks.getNavigationMarker()` and `Perf.getEntriesByType()` methods
 *
 */
export declare type PerfMarksPerformanceNavigationTiming = PerformanceNavigationTiming | {
    [key: string]: any;
};
/**
 * Gets the result for all marks that matches with the given mark name
 *
 * @param markName - Performance marker to be checked
 *
 * @returns PerfMarksPerformanceNavigationTiming[]
 *
 */
declare const getEntriesByType: (entryName: string) => PerfMarksPerformanceNavigationTiming[];
/**
 * Gets the marks for `navigation` loaded mark
 *
 * @returns PerfMarksPerformanceNavigationTiming[]
 *
 */
declare const getNavigationMarker: () => PerfMarksPerformanceNavigationTiming;
export { getNavigationMarker, getEntriesByType };
