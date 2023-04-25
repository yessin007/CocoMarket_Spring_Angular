import { Observable } from 'rxjs';
import { Attributes, Hooks } from './types';
export declare function lazyLoadImage<E>(hooks: Hooks<E>, attributes: Attributes): (evntObservable: Observable<E>) => Observable<boolean>;
