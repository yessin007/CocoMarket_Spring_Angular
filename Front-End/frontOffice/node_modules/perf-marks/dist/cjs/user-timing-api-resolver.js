"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var is_nodejs_env_1 = require("./is-nodejs-env");
if (is_nodejs_env_1.isNodeJSEnv &&
    !global.PerformanceObserver &&
    !global.performance &&
    module &&
    typeof module.require === 'function') {
    /**
     * Requires a module which is protected against bundler minification.
     *
     * @param pkg The module path to resolve
     */
    var dynamicRequire = function (mod, pkg) {
        return mod.require(pkg);
    };
    try {
        // eslint-disable-next-line @typescript-eslint/no-var-requires
        var _a = dynamicRequire(module, 'perf_hooks'), PerformanceObserver_1 = _a.PerformanceObserver, performance_1 = _a.performance;
        global.PerformanceObserver = PerformanceObserver_1;
        global.performance = performance_1;
    }
    catch (error) {
        throw new Error("Your NodeJS application doesn't support 'perf_hooks'. " + error);
    }
}
