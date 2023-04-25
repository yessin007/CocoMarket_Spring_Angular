"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.isNodeJSEnv = void 0;
/**
 * Boolean with the result of the check if package
 * is running on the browser or in a NodeJS environment
 *
 * @returns boolean
 *
 */
var isNodeJSEnv = Object.prototype.toString.call(typeof process !== 'undefined' ? process : 0) === '[object process]';
exports.isNodeJSEnv = isNodeJSEnv;
