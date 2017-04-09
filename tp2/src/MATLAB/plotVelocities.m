function plotVelocities(input, output)

    % Plot the average velocity field.
    quiver(av_vel_x_coords, av_vel_y_coords, av_vel_x_comps, av_vel_y_comps);
    
    % Plot the channel boundaries.
    hold on;
    plot([1; grain_x], [0.75; 0.75], 'k-');
    hold on;
    plot([1; grain_x], [grain_y + 0.25; grain_y + .25], 'k-');
    
    % Display the flow obstacle.
    obstacle_x = zeros(1, nnz(obstacle));
    obstacle_y = zeros(1, nnz(obstacle));
    k = 1;
    
    for (i = 1:1:numnodes_x)
        for (j = 1:1:numnodes_y)
            if (obstacle(i, j) == 1)
                obstacle_x(k) = 0.5 + (numnodes_x ./ (grain_size .* (numnodes_x - 1))) .* (i - 1);
                obstacle_y(k) = 0.5 + (numnodes_y ./ (grain_size .* (numnodes_y - 1))) .* (j - 1);
                k = k + 1;
            end
        end
    end
    
    hold on; 
    plot(obstacle_x, obstacle_y, 'r-');
    axis equal;
    
end

