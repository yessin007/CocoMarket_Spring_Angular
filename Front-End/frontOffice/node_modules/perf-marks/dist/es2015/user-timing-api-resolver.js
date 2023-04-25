import { isNodeJSEnv } from './is-nodejs-env';
if (isNodeJSEnv &&
    !global.PerformanceObserver &&
    !global.performance &&
    module &&
    typeof module.require === 'function') {
    /**
     * Requires a module which is protected against bundler minification.
     *
     * @param pkg The module path to resolve
     */
    const dynamicRequire = (mod, pkg) => {
        return mod.require(pkg);
    };
    try {
        // eslint-disable-next-line @typescript-eslint/no-var-requires
        const { PerformanceObserver, performance } = dynamicRequire(module, 'perf_hooks');
        global.PerformanceObserver = PerformanceObserver;
        global.performance = performance;
    }
    catch (error) {
        throw new Error(`Your NodeJS application doesn't support 'perf_hooks'. ${error}`);
    }
}
