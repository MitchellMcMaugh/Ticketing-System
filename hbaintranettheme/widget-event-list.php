<?php
/**
 * The template is used for displaying the Event List widget if the placeholder option isn't used.
 *
 * You can use this to edit how the output of the event list widget. For the shortcode [eo_events] see shortcode-event-list.php
 *
 * For a list of available functions (outputting dates, venue details etc) see http://codex.wp-event-organiser.com
 *
 ***************** NOTICE: *****************
 *  Do not make changes to this file. Any changes made to this file
 * will be overwritten if the plug-in is updated.
 *
 * To overwrite this template with your own, make a copy of it (with the same name)
 * in your theme directory. See http://docs.wp-event-organiser.com/theme-integration for more information
 *
 * WordPress will automatically prioritise the template in your theme directory.
 ***************** NOTICE: *****************
 *
 * @package Event Organiser (plug-in)
 * @since 1.7
 */
global $eo_event_loop,$eo_event_loop_args;

//The list ID / classes
$id      = ( $eo_event_loop_args['id'] ? 'id="'.$eo_event_loop_args['id'].'"' : '' );
$classes = $eo_event_loop_args['class'];
?>

<?php if ( $eo_event_loop->have_posts() ) :  ?>

	<ul <?php echo $id; ?> class="<?php echo esc_attr( $classes );?>" > 

		<?php while ( $eo_event_loop->have_posts() ) :  $eo_event_loop->the_post(); ?>

			<?php
				//Generate HTML classes for this event
				$eo_event_classes = eo_get_event_classes();

				//For non-all-day events, include time format
				$format = eo_get_event_datetime_format();
			?>

			<li class="<?php echo esc_attr( implode( ' ',$eo_event_classes ) ); ?>" >
				<small>
					<?php echo __( '','eventorganiser' ) . ' '.eo_get_the_start( $format ); ?>
					<?php if( get_field('event_city') ): ?>
					 - <?php the_field('event_city'); ?>
					<?php endif; ?>
				</small>
				<p><a href="<?php the_permalink(); ?>" title="<?php the_title_attribute(); ?>" ><?php the_title(); ?></a></p>
			</li>

		<?php endwhile; ?>

	</ul>

<?php elseif ( ! empty( $eo_event_loop_args['no_events'] ) ) :  ?>

	<ul id="<?php echo esc_attr( $id );?>" class="<?php echo esc_attr( $classes );?>" > 
		<li class="eo-no-events" > <?php echo $eo_event_loop_args['no_events']; ?> </li>
	</ul>

<?php endif;
