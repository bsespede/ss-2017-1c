function makeAnimation(inputFolder, fileName, frameRate)

    % read output files
	inputFiles = dir(fullfile(inputFolder, '*.txt'));
    
    imageFolder = strcat(inputFolder, 'images/');
    videoFolder = strcat(inputFolder, 'video/');
    mkdir(imageFolder);
    mkdir(videoFolder);
    
    % make plots
    for i = 1:length(inputFiles)
        velocitiesFile = strcat(inputFolder, fileName, '-', num2str(i), '.txt');
        boundariesFile = strcat(inputFolder, fileName, '-boundaries.txt');
        dataFile = strcat(inputFolder, fileName, '-data.txt');
        outputImage = strcat(imageFolder, fileName, '-', num2str(i), '.png');
        plotVelocities(velocitiesFile, boundariesFile, dataFile, outputImage);
    end
    
    % create video file
    outputVideo = VideoWriter(fullfile(videoFolder, fileName, '-video.avi'));
    outputVideo.FrameRate = frameRate;
    open(outputVideo);
    
    % renders frames
    for i = 1:length(inputFiles)
        img = imread(fullfile(imageFolder, strcat(fileName, '-', num2str(i), '.png')));
        writeVideo(outputVideo, img);
    end
    close(outputVideo);

end