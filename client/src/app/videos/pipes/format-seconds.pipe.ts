import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'formatSeconds',
    standalone: true
})
export class FormatSecondsPipe implements PipeTransform {
    transform(seconds: number): string {
        const hours = Math.floor(seconds / 3600);
        const minutes = Math.floor((seconds % 3600) / 60);
        const remainingSeconds = seconds % 60;

        let formattedTime = '';

        if (hours > 0) {
            formattedTime += `${hours.toString().padStart(2, '0')}:`;
        }

        formattedTime += `${minutes.toString().padStart(2, '0')}:${remainingSeconds.toString().padStart(2, '0')}`;

        return formattedTime;
    }
}