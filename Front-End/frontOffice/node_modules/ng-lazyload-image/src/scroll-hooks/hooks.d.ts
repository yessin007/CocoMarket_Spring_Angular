import { Observable } from 'rxjs';
import { SharedHooks } from '../shared-hooks/hooks';
import { Attributes } from '../types';
export declare class ScrollHooks extends SharedHooks<Event | string> {
    protected getWindow: () => Window & typeof globalThis;
    private readonly scrollListeners;
    getObservable(attributes: Attributes<Event | string>): Observable<Event | string>;
    isVisible(event: Event | string, attributes: Attributes): boolean;
    sampleObservable<T>(obs: Observable<T>, scheduler?: any): Observable<T | ''>;
    getScrollListener: (scrollTarget?: HTMLElement | Window) => Observable<Event | ''>;
}
