function plotVelocities(velocitiesFile, boundariesFile, dataFile, output)

    % Get vectors from input
    [velocities] = importdata(velocitiesFile, ',');

    % Plot the average velocity field.
    quiver(velocities(:, 3), velocities(:, 4), velocities(:, 1), velocities(:, 2));
    
    % Plot the channel boundaries.
    [data] = importdata(dataFile, ',');
    grainX = data(1, 1) / data(1, 3);
    grainY = data(1, 2) / data(1, 3);
    hold on;
    plot([1; grainX], [0.75; 0.75], 'k-');
    hold on;
    
    % Display the flow obstacle.
    [boundaries] = importdata(boundariesFile)
    [boundariesSize] = size(boundaries)
    obstacle_x = zeros(1, boundariesSize(1));
    obstacle_y = zeros(1, boundariesSize(1));
    
    for (i = 1:1:boundariesSize(1))
        obstacle_x(i) = 0.5 + (data(1,1) ./ (data(1,3) .* (data(1,1) - 1))) .* boundaries(i, 1);
        obstacle_y(i) = 0.5 + (data(1,1) ./ (data(1,3) .* (data(1,2) - 1))) .* boundaries(i, 2);
    end
    
    hold on; 
    plot(obstacle_x, obstacle_y, 'r-');
    axis equal;
    
    print(output, '-dpng');
    
end