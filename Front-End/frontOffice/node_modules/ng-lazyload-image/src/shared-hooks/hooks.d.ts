import { ObservableInput } from 'rxjs';
import { Attributes, Hooks } from '../types';
export declare abstract class SharedHooks<E> extends Hooks<E> {
    setup(attributes: Attributes): void;
    finally(attributes: Attributes): void;
    loadImage(attributes: Attributes): ObservableInput<string>;
    setErrorImage(error: Error, attributes: Attributes): void;
    setLoadedImage(imagePath: string, attributes: Attributes): void;
    isDisabled(): boolean;
    skipLazyLoading(attributes: Attributes): boolean;
    isBot(attributes?: Attributes): boolean;
}
