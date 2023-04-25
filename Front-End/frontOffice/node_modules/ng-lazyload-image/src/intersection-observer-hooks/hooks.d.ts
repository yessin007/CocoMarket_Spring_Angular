import { Observable } from 'rxjs';
import { SharedHooks } from '../shared-hooks/hooks';
import { Attributes } from '../types';
export declare class IntersectionObserverHooks extends SharedHooks<{
    isIntersecting: boolean;
}> {
    private readonly observers;
    private readonly intersectionSubject;
    private readonly uniqKey;
    getObservable(attributes: Attributes<{
        isIntersecting: boolean;
    }>): Observable<{
        isIntersecting: boolean;
    }>;
    isVisible(event: {
        isIntersecting: boolean;
    }): boolean;
    private loadingCallback;
}
