function makeAnimation(inputFolder, frameRate)

    % read output files
	inputFiles = dir(fullfile(inputFolder, '*.output'));
    
    imageFolder = strcat(inputFolder, 'images/');
    videoFolder = strcat(inputFolder, 'video/');
    mkdir(imageFolder);
    mkdir(videoFolder);
    
    % make density plots
    for i = 1:length(inputFiles)
        inputFile = strcat(inputFolder, num2str(i), '.output');
        outputImage = strcat(imageFolder, num2str(i), '.png');
        plotDensity(inputFile, outputImage);
    end
    
    % create video file
    outputVideo = VideoWriter(fullfile(videoFolder, 'simulation.avi'));
    outputVideo.FrameRate = frameRate;
    open(outputVideo);
    
    % renders frames
    for i = 1:length(inputFiles)
        img = imread(fullfile(imageFolder, strcat(num2str(i), '.png')));
        writeVideo(outputVideo, img);
    end
    close(outputVideo);

end